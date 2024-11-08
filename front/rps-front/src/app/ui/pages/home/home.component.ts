import { Component } from '@angular/core';
import { BasicPageComponent } from '../basic-page/basic-page.component';
import { CardMenuComponent } from "../../menu/button-menu/button-menu.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [BasicPageComponent, CardMenuComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  pageId :string = "home-page"
  menuOptionsArray = [
    { id: 1, name: 'Play', link: '/play', color: "green" },
    { id: 2, name: 'Profile', link: '/profile',color: "blue" },
    { id: 3, name: 'Options', link: '/options',color: "blue" },
    { id: 4, name: 'Log out', link: '#',color: "red" }
  ];
}
