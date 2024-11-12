import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PagesModule } from './ui/pages/pages.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PagesModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'rps-front';
}
