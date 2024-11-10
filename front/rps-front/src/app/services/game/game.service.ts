import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ModeOption} from "../../model/mode-option.model";

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private apiUrl = 'http://localhost:8080/game/modes';


  constructor(private http: HttpClient) {}

  getGameModes(): Observable<ModeOption[]> {
    return this.http.get<ModeOption[]>(this.apiUrl);
  }

  queuePlayer(selectedMode: ModeOption): void {
  }
}