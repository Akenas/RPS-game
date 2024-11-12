import { Component, Input } from '@angular/core';
import { GameMatch } from '../../model/gameMatch.model';
import { RoundsVisorComponent } from "./rounds-visor/rounds-visor.component";
import { OptionPickerComponent } from "./option-picker/option-picker.component";
import { GameService } from '../../services/game/game.service';
import { PickVisorComponent } from "./pick-visor/pick-visor.component";
import { Round } from '../../model/roud.model';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [RoundsVisorComponent, OptionPickerComponent, PickVisorComponent],
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

  getLastPlayedRound(): Round | undefined  {
    
    const playedRounds = this.match!.rounds.filter(round => round.player1Pick !== null || round.player2Pick !== null);
    
    return playedRounds.reduce<Round | undefined>((latestRound, currentRound) => {
      return (latestRound === undefined || currentRound.id > latestRound.id) ? currentRound : latestRound;
    }, undefined);
  }
}
