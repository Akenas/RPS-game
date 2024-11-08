import { Component } from '@angular/core';

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
}
