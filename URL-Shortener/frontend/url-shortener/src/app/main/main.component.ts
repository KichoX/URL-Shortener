import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { URL } from './URL';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent {
  toShorten!: string;
  url!: URL;
  clicks!: number;
  showClicks = false;

  constructor(private apiService: ApiService) {}

  shorten() {
    this.apiService.shorten(this.toShorten).subscribe(
      (response) => {
        console.log(response);
        this.url = response;
        console.log(this.url);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  redirectUrl() {
    const url = 'http://localhost:8080/redirect/' + this.url.shortUrl;
    window.location.href = url;
  }

  noClicks() {
    this.showClicks = !this.showClicks;
    this.clicks = this.url.clicks;
  }
}
