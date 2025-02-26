import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormComponent } from '../form/form.component'; // <-- your existing form

@Component({
  selector: 'app-frontend-page',
  standalone: true,
  imports: [CommonModule, FormComponent],
  templateUrl: './frontend.component.html',
  styleUrls: ['./frontend.component.css']
})
export class frontend {}