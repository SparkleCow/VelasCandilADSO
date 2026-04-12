import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { TestimonialsComponent } from '../../../testimonials/testimonials.component';
import { AboutComponent } from '../../../about/about.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    RouterModule,
    MatButtonModule,
    MatIconModule,
    TestimonialsComponent,
    AboutComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  constructor(private router: Router) {}

  redirectAtCatalog() {
    this.router.navigate(['/candles']);
  }
}
