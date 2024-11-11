import { Component, Input } from '@angular/core';
import { GameMatch } from '../../model/gameMatch.model';
import { RoundsVisorComponent } from "./rounds-visor/rounds-visor.component";

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [RoundsVisorComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {
  
  @Input() match: GameMatch | null = null;
}
