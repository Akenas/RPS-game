import { Component, Input } from '@angular/core';
import { GameMatch } from '../../model/gameMatch.model';
import { RoundsVisorComponent } from "./rounds-visor/rounds-visor.component";
import { OptionPickerComponent } from "./option-picker/option-picker.component";
import { GameService } from '../../services/game/game.service';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [RoundsVisorComponent, OptionPickerComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {

  @Input() match: GameMatch | null = null;

  constructor(private gameService: GameService) {}
  
  handleOptionLocked(option: number | null) {
    if (option !== null) {
      this.gameService.sendMatchRoundPick(option, this.match!.id);
    }
  }
}
