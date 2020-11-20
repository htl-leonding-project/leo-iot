import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  /**
   *The articles title
   *
   * @type {String}
   * @memberof NewsComponent
   */
  @Input() title: String;

  /**
   *The articles date
   *
   * @type {String}
   * @memberof NewsComponent
   */
  @Input() date: Date;

  /**
   *The articels body text which will be cut to 250 characters
   *
   * @type {String}
   * @memberof NewsComponent
   */
  @Input() body: String;

  displayBody: String;

  constructor() { }

  ngOnInit() {
    if (this.body) {
      this.displayBody = this.body.substr(0, 250);
    }
  }

}
