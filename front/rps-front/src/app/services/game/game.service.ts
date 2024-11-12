import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { Observable, ReplaySubject, tap } from 'rxjs';
import SockJS from 'sockjs-client/dist/sockjs';
import { ModeOption } from "../../model/mode-option.model";
import { Player } from '../../model/player.model';
import { GameMatch } from '../../model/gameMatch.model';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private apiUrl = 'http://localhost:8080/game';
  private socketUrl = 'http://localhost:8080/ws/game';
  private connection: CompatClient | undefined = undefined;
  private connectionReady = new ReplaySubject<boolean>(1);
  private currentPlayerSubject = new ReplaySubject<Player | null>(1);
  public currentPlayer = this.currentPlayerSubject.asObservable();

  constructor(private http: HttpClient) {

    this.fetchPlayerData().subscribe();
    this.initConnection();

  }

  initConnection(): void {
    this.connection = Stomp.over(() => new SockJS(this.socketUrl));
    const token = localStorage.getItem("jwtToken");

    this.connection.connect(
      { "Authorization": "Bearer " + token },
      () => {
        console.log('WebSocket connection established');
        this.connectionReady.next(true); 
      },
      (error: any) => {
        console.error('WebSocket connection error:', error);
        this.connectionReady.next(false);
      }
    );
  }

  waitForConnection(): Observable<boolean> {
    return this.connectionReady.asObservable();
  }

  getGameModes(): Observable<ModeOption[]> {
    return this.http.get<ModeOption[]>(`${this.apiUrl}/modes`);
  }

  fetchPlayerData(): Observable<Player> {
    return this.http.get<Player>(`${this.apiUrl}/player`).pipe(
      tap(player => this.currentPlayerSubject.next(player)) 
    );
  }

  getPlayerData(): Observable<Player | null> {
    return this.currentPlayer;
  }

  queuePlayer(playerId: number, selectedModeId: number): Observable<GameMatch> {

    return new Observable<GameMatch>((observer) => {
      const subscription = this.connection?.subscribe(`/queue/${playerId}`, (message) => {
        const match: GameMatch = JSON.parse(message.body);
        observer.next(match); 
      });

      this.connection?.send(
        '/app/queue/join',
        {},
        JSON.stringify({ type: 'queue-join', modeId: selectedModeId, playerId: playerId })
      );

      return () => {
        subscription?.unsubscribe();
      };
    });
  }
  
  unqueuePlayer(playerId: number, selectedModeId: number): void {

    if(this.connection?.connected){
      this.connection?.send(
        "/app/queue/leave",
        {},
        JSON.stringify({type:'queue-leave', modeId: selectedModeId, playerId: playerId})
      );
    }
  }

  subscribeToMatch(matchId: string): Observable<GameMatch> {
    return new Observable<GameMatch>((observer) => {
      const subscription = this.connection?.subscribe(`/match/${matchId}`, (message) => {
        const matchUpdate: GameMatch = JSON.parse(message.body);
        observer.next(matchUpdate);
      });

      return () => {
        subscription?.unsubscribe();
      };
    });
  }

  sendMatchRoundPick(optionId: number, matchId: string): void {
    this.currentPlayerSubject.subscribe((player) => {
      const playerId = player?.id;
      if (playerId && this.connection?.connected) {
        const event = {
          type: 'match-pick',
          playerId: playerId,
          pick: optionId,
          matchId: matchId
        };
        this.connection.send('/app/match/pick', {}, JSON.stringify(event));
      }
    }).unsubscribe(); // Unsubscribe immediately to avoid keeping the subscription open
  }

  forfeitMatch(matchId: string): void {
    this.currentPlayerSubject.subscribe((player) => {
      const playerId = player?.id;
      if (playerId && this.connection?.connected) {
        const event = {
          type: 'match-forfeit',
          playerId: playerId,
          matchId: matchId
        };
        this.connection.send('/app/match/forfeit', {}, JSON.stringify(event));
      }
    }).unsubscribe(); // Unsubscribe immediately to avoid keeping the subscription open
  }

  disconnect(): void {
    if (this.connection && this.connection.connected) {
        this.connection.disconnect(() => {
            console.log('WebSocket connection disconnected.');
        });
    }
}
}