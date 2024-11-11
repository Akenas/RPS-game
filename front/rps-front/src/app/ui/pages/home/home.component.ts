import { Component } from '@angular/core';
import { Player } from '../../../model/player.model';
import { GameService } from '../../../services/game/game.service';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomePageComponent {
  pageId :string = "home-page"
  menuOptionsArray = [
    { id: 1, name: 'Play', link: '/play', color: "green" },
    { id: 2, name: 'Profile', link: '/profile',color: "blue" },
    { id: 3, name: 'Options', link: '/options',color: "blue" },
    { id: 4, name: 'Log out', link: '#',color: "red" }
  ];

  player: Player | null = null;

  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.gameService.getPlayerData().subscribe(player => {
      this.player = player;
    });
  }


}
