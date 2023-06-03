import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AllURL } from './main/allUrls';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  postURL = 'http://localhost:8080/shorten';
  redirectURL = 'http://localhost:8080/redirect/';
  updateURL = 'http://localhost:8080/update';
  deleteURL = 'http://localhost:8080/delete/';
  getAllURL = 'http://localhost:8080/getall';

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

  update(oldShortUrl: string, newShortUrl: string) {
    const endpoint = this.updateURL + '?oldCode=' + oldShortUrl + '&newCode=' + newShortUrl;
    return this.http.put(endpoint, {});
  }

  delete(shortUrl: string) {
    return this.http.delete(this.deleteURL + shortUrl);
  }

  getAll(): Observable<AllURL> {
    return this.http.get<AllURL>(this.getAllURL);
  }
}
