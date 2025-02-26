import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DataFetcherService } from '../Services/data-fetcher.service';

const MAX_ALLOWED = 400001;

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {
  MAX_ALLOWED = MAX_ALLOWED;
  form: FormGroup;
  result?: string; 
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private dataFetcher: DataFetcherService
  ) {
    this.form = this.fb.group({
      number: [null, [
        Validators.required,
        Validators.min(0),
        Validators.max(MAX_ALLOWED)
        
      ]]
    });
  }

  onSubmit() {
    if (this.form.valid) {
      const number = this.form.value.number;

      this.dataFetcher.fetchLabSeq(number).subscribe({
        next: (response) => {
          this.result = response;  
          this.errorMessage = '';
        },
        error: (err) => {
          this.errorMessage = err.message || 'Error fetching data';
          this.result = undefined;
        }
      });
    }
  }
}
