  import { Component } from '@angular/core';
  import { CommonModule } from '@angular/common';
  import { HttpClientModule } from '@angular/common/http';
  import { frontend } from './frontend/frontend.component';

  @Component({
    selector: 'app-root',
    standalone: true,
    imports: [CommonModule, HttpClientModule,  frontend],
    templateUrl:'./app.component.html',
  
    
  })
  export class AppComponent {}