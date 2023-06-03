import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { URL } from './URL';
import { OnInit } from '@angular/core';
import { AllURL } from './allUrls';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  toShorten!: string;
  oldShortUrl!: string;
  newShortUrl!: string;
  toDelete!: string;
  url!: URL;
  clicks!: number;
  showClicks = false;
  allUrls: URL[] = [];
  ascending = true;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getAll().subscribe(
      (response) => {
        console.log(response);
        for (let i = 0; i < response.length; i++) {
          this.allUrls[i] = JSON.parse(response[i]);
        }
        console.log(this.allUrls);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  shorten() {
    if (this.toShorten) {
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
    } else {
      alert("URL can't be blank!");
    }
  }

  redirectUrl() {
    const url = 'http://localhost:8080/redirect/' + this.url.shortUrl;
    window.location.href = url;
  }

  noClicks() {
    this.showClicks = !this.showClicks;
    this.clicks = this.url.clicks;
  }

  update() {
    if (this.oldShortUrl && this.newShortUrl) {
      this.apiService.update(this.oldShortUrl, this.newShortUrl).subscribe(
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      alert("URLs cant't be blank!");
    }
  }

  delete() {
    if (this.toDelete) {
      this.apiService.delete(this.toDelete).subscribe(
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      alert("Input can't be blank!");
    }
  }

  ascendingFunc() {
    if (!this.ascending) {
      this.ascending = !this.ascending;
      this.allUrls.reverse();
    }
  }

  descendingFunc() {
    if (this.ascending) {
      this.ascending = !this.ascending;
      this.allUrls.reverse();
    }
  }
}
