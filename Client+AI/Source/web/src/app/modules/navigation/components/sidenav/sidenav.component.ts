import { Component, OnInit, ChangeDetectorRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MediaMatcher } from '@angular/cdk/layout';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  /**
   *Containing the sidenav open state
   *
   * @memberof SidenavComponent
   */
  sidenavOpened = false;

  /**
   *Mobile Query for breakpoints
   *
   * @type {MediaQueryList}
   * @memberof SidenavComponent
   */
  mobileQuery: MediaQueryList;

  private _mobileQueryListener: () => void;

  /**
   *Creates an instance of SidenavComponent.
   * @param {Router} router
   * @param {ChangeDetectorRef} changeDetectorRef
   * @param {MediaMatcher} media
   * @memberof SidenavComponent
   */
  constructor(private router: Router,
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher) {
    this.mobileQuery = media.matchMedia('(max-width: 850px)');
    this._mobileQueryListener = () => {
      changeDetectorRef.detectChanges();
    };
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnInit() {
  }

  /**
   *Toggles the sidenav by inverting the `sidenavOpened` property
   *
   * @memberof SidenavComponent
   */
  toggleSidenav() {
    this.sidenavOpened = !this.sidenavOpened;
  }
}
