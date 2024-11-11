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
  private currentPlayerSubject = new ReplaySubject<Player | null>(1);
  public currentPlayer = this.currentPlayerSubject.asObservable();

  constructor(private http: HttpClient) {

    this.fetchPlayerData().subscribe();
    this.initConnection();

  }

  initConnection(): void{
    const socket = new SockJS(this.socketUrl);
    this.connection = Stomp.over(socket);
    const token = localStorage.getItem("jwtToken");
    this.connection.connect({"Authorization":"Bearer " + token}, () => {});
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

  subscribeToMatch(matchId : number): void {

    this.connection?.subscribe("/match/"+matchId, (message) => console.log(message))
  }
}