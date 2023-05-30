import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  postURL = 'http://localhost:8080/shorten';
  redirectURL = 'http://localhost:8080/redirect/';

  constructor(private http: HttpClient) {}

  shorten(toShorten: string): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    const requestBody = {
      longUrl: toShorten,
    };

    return this.http.post(this.postURL, requestBody, { headers });
  }
}
