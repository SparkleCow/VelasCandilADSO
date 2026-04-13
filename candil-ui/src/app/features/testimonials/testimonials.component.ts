import { Component } from '@angular/core';
import {
  AfterViewInit,
  ElementRef,
  QueryList,
  ViewChildren,
} from '@angular/core';

@Component({
  selector: 'app-testimonials',
  imports: [],
  templateUrl: './testimonials.component.html',
  styleUrl: './testimonials.component.css',
})
export class TestimonialsComponent implements AfterViewInit {
  @ViewChildren('cardRef') cards!: QueryList<ElementRef>;

  ngAfterViewInit() {
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add('animate');
          }
        });
      },
      { threshold: 0.2 },
    );

    this.cards.forEach((card) => observer.observe(card.nativeElement));
  }
}
