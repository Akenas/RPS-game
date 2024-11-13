import { Component, QueryList, ViewChildren } from '@angular/core';
import { GameService } from '../../../services/game/game.service';
import { ModeOption } from '../../../model/mode-option.model';
import { GameMatch } from '../../../model/gameMatch.model';
import { Player } from '../../../model/player.model';
import { OverlayMenuComponent } from '../../menu/overlay-menu/overlay-menu.component';
import { Router } from '@angular/router';
import { OptionPickerComponent } from '../../game/option-picker/option-picker.component';



@Component({
  selector: 'app-play',
  standalone: false,
  templateUrl: './play.component.html',
  styleUrl: './play.component.css'
})
export class PlayPageComponent {

  @ViewChildren(OverlayMenuComponent) overlayMenus!: QueryList<OverlayMenuComponent>;
  @ViewChildren(OptionPickerComponent) optionPicker!: QueryList<OptionPickerComponent>;

  pageId :string = "play-page"
  selectedMode : ModeOption | null = null;
  modes: ModeOption[] = [];
  match: GameMatch | null = null ;
  player: Player | null = null;
  menuOptionsArray1 = [

    { id: 1, name: 'Options', function: ()=>{}, color: "", enabled: false},
    { id: 2, name: 'Forfeit', function:()=>{this.forfeitMatch()}, color: "red",enabled: true }
  ];

  menuOptionsArray2 = [

    { id: 1, name: 'Rematch', function: ()=>{this.handleRematch()}, color: "green", enabled: true},
    { id: 2, name: 'Go Home', function:()=>{this.router.navigate(["/"])}, color: "blue",enabled: true }
  ];

  constructor(private gameService: GameService, private router: Router){}

  ngOnInit(): void {
    
    const savedMatch = localStorage.getItem('savedMatch');
    if (savedMatch) {
      this.match = JSON.parse(savedMatch);
      this.selectedMode = this.match!.mode;

      this.gameService.waitForConnection().subscribe((connected) => {
        if (connected && this.match) {
          this.handleMatchSubscription(this.match);
        }
      });
    }
    
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
    this.gameService.initConnection();
  }

  ngOnDestroy(): void {
    // Perform cleanup here
    console.log('Component is being destroyed.');
    localStorage.removeItem('savedMatch');
    this.gameService.disconnect();
  }

  handleModeSelected(item: ModeOption) {
    this.selectedMode = item;
    this.gameService.queuePlayer(this.player!.id, this.selectedMode.id).subscribe(match => {
      this.match = match; 
      localStorage.setItem('savedMatch', JSON.stringify(match));
      this.handleMatchSubscription(this.match);
    });
  }

  handleCancelQueue() {
    this.gameService.unqueuePlayer(this.player!.id, this.selectedMode!.id);
    this.selectedMode = null
  }

  handleMatchSubscription(match: GameMatch) {
    this.gameService.subscribeToMatch(match.id).subscribe(updatedMatch => {
      this.match = updatedMatch;
      if(!this.match.ongoing){
        setTimeout(() => {
          this.openOverlay(1);
        }, 2000);
       
      }else{
        localStorage.setItem('savedMatch', JSON.stringify(updatedMatch));
      }
     
    });
  }

  openOverlay(index: number) {
    const overlay = this.overlayMenus.toArray()[index];
    if (overlay) {
      overlay.openOverlay();
    }
  }

  closeOverlay(index: number) {
    const overlay = this.overlayMenus.toArray()[index];
    if (overlay) {
      overlay.closeOverlay();
    }
  }

  handleRematch(){
    this.handleModeSelected(this.match!.mode); 
    this.closeOverlay(1);
  }

  forfeitMatch(){
    this.gameService.forfeitMatch(this.match!.id)
    this.router.navigate(["/"])
  }

  goHome(){
    this.router.navigate(["/"])
  }
}


