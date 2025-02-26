import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataFetcherService {
  constructor(private http: HttpClient) {}

  fetchLabSeq(n: string) {
    
    return this.http.get(`http://localhost:8080/labseq/${n}`, {
      responseType: 'text'  // <== Passes from number to string
    }).pipe(
      catchError(error => {
        return throwError(() => new Error(
          error.error?.message || 'Server request failed'
        ));
      })
    );
  }
}