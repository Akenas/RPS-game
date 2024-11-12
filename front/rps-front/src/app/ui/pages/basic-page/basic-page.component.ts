import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-basic-page',
  standalone: false,
  templateUrl: './basic-page.component.html',
  styleUrl: './basic-page.component.css'
})
export class BasicPageComponent {

  @Input() pageId: string = 'default-id';
}
