import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

interface MenuOption {
  id: number;
  name: string;
  function:() => void
  color: string;
  enabled: boolean;
}

@Component({
  selector: 'app-card-menu',
  standalone: true,
  templateUrl: './button-menu.component.html',
  styleUrls: ['./button-menu.component.css'],
  imports: [CommonModule]
})
export class CardMenuComponent {
  @Input() menuOptions: MenuOption[] = [];
}