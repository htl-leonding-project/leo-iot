import { Component, OnInit, Input } from '@angular/core';
import {footerText} from 'src/app/config/footerText';

/**
 *Displaying a running text (e.g. html marquee)
 *
 * @export
 * @class FooterTextComponent
 */
@Component({
  selector: 'app-footer-text',
  templateUrl: './footer-text.component.html',
  styleUrls: ['./footer-text.component.scss']
})
export class FooterTextComponent implements OnInit {

  /**
   *The displayed text
   *
   * @type {string}
   * @memberof FooterTextComponent
   */
  @Input() text: string;

  /**
   *Creates an instance of FooterTextComponent.
   * @memberof FooterTextComponent
   */
  constructor() { }

  ngOnInit() {
    this.text = footerText.content;
  }
}
