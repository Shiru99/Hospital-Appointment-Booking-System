import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root',
})
export class EmployeeServiceService {
  constructor(private http: HttpClient) {}

  private _url: string = '/assets/data/employees.json';

  getEmployees(): Observable<Employee[]> {
    return this.http
      .get<Employee[]>(this._url)
      .pipe(catchError(this.errorHandler));
  }

  errorHandler(errorHandler: HttpErrorResponse) {
    return throwError(errorHandler.message || 'Server Error');
  }
}
