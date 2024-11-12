import { Component } from '@angular/core';
import { Player } from '../../../model/player.model';
import { GameService } from '../../../services/game/game.service';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomePageComponent {
  pageId :string = "home-page"
  menuOptionsArray = [
    { id: 1, name: 'Play', function: ()=>{this.router.navigate(['/play']);}, color: "green", enabled:true },
    { id: 2, name: 'Profile', function: ()=>{this.router.navigate(['/profile']);},color: "", enabled:false},
    { id: 3, name: 'Options', function: ()=>{this.router.navigate(['/options']);},color: "", enabled:false},
    { id: 4, name: 'Log out', function: ()=>{this.authService.logout()},color: "red", enabled:true}
  ];

  player: Player | null = null;

  constructor(private gameService: GameService, private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.gameService.getPlayerData().subscribe(player => {
      this.player = player;
    });
  }


}
