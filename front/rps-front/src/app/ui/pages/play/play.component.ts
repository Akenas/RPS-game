import { Component } from '@angular/core';
import { GameService } from '../../../services/game/game.service';
import { ModeOption } from '../../../model/mode-option.model';
import { GameMatch } from '../../../model/gameMatch.model';
import { Player } from '../../../model/player.model';



@Component({
  selector: 'app-play',
  standalone: false,
  templateUrl: './play.component.html',
  styleUrl: './play.component.css'
})
export class PlayPageComponent {

  pageId :string = "play-page"
  selectedMode : ModeOption | null = null;
  modes: ModeOption[] = [];
  match: GameMatch | null = null ;
  player: Player | null = null;

  constructor(private gameService: GameService){}

  ngOnInit(): void {
    
    this.gameService.getGameModes().subscribe({
      next: (data: ModeOption[]) => {
        this.modes = data;
      },
      error: (error) => {
        console.error('Error fetching game modes:', error);
      }
    });

    this.gameService.getPlayerData().subscribe(player => {
      this.player = player;
    });
  }

  handleModeSelected(item: ModeOption) {
    this.selectedMode = item;
    this.gameService.queuePlayer(this.player!.id, this.selectedMode.id).subscribe(match => {
      this.match = match; 
    });
  }

  handleCancelQueue() {
    this.gameService.unqueuePlayer(this.player!.id, this.selectedMode!.id);
    this.selectedMode = null
  }
}
