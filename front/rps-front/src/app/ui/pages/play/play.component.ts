import { Component } from '@angular/core';
import { GameService } from '../../../services/game/game.service';
import { ModeOption } from '../../../model/mode-option.model';



@Component({
  selector: 'app-play',
  standalone: false,
  templateUrl: './play.component.html',
  styleUrl: './play.component.css'
})
export class PlayPageComponent {

  pageId :string = "play-page"
  selectedMode?: ModeOption;
  modes: ModeOption[] = [];

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
  }

  handleModeSelected(item: ModeOption) {
    this.selectedMode = item;
    this.gameService.queuePlayer(this.selectedMode);
  }
}
