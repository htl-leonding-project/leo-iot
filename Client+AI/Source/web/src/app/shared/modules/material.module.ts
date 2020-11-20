import { NgModule } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import {
  MatButtonModule,
  MatToolbarModule,
  MatListModule,
  MatDividerModule,
  MatMenuModule,
  MatIconModule,
  MatCardModule,
  MatGridListModule,
  MatIconRegistry,
  MatDatepickerModule,
  MatNativeDateModule,
  MatInputModule,
  MatSidenavModule,
  MatSliderModule,
  MatFormFieldModule,
  MatButtonToggleModule,
  MatTabsModule,
  MatTableModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSortModule,
  MatRadioModule,
  MatExpansionModule,
  MatCheckboxModule,
  MatProgressBarModule,
  MatBadgeModule,
  MatAutocompleteModule,
  MatSlideToggleModule,
  MatRippleModule
} from '@angular/material';

@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [
    MatButtonModule,
    MatToolbarModule,
    MatListModule,
    MatDividerModule,
    MatMenuModule,
    MatIconModule,
    MatCardModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatSidenavModule,
    MatAutocompleteModule,
    MatSliderModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonToggleModule,
    MatTabsModule,
    MatTableModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    MatRadioModule,
    MatExpansionModule,
    MatCheckboxModule,
    MatProgressBarModule,
    MatBadgeModule,
    MatSlideToggleModule,
    MatRippleModule
  ],
  providers: []
})
export class MaterialModule {
  // adding svg files as mat-icon (usage: <mat-icon svgIcon="iconName"></mat-icon>)
  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    if (iconRegistry) {
      iconRegistry.addSvgIcon(
        'facebook',
        sanitizer.bypassSecurityTrustResourceUrl('assets/icons/social/facebook.svg'));
      iconRegistry.addSvgIcon(
        'facebook-white',
        sanitizer.bypassSecurityTrustResourceUrl('assets/icons/social/facebook-white.svg'));
      // LinkedIn icon
      iconRegistry.addSvgIcon(
        'linkedin',
        sanitizer.bypassSecurityTrustResourceUrl('assets/icons/social/linkedin.svg'));
      iconRegistry.addSvgIcon(
        'linkedin-white',
        sanitizer.bypassSecurityTrustResourceUrl('assets/icons/social/linkedin-white.svg'));
      // Website icon
      iconRegistry.addSvgIcon(
        'leonding',
        sanitizer.bypassSecurityTrustResourceUrl('assets/icons/htl/logo.svg'));
      iconRegistry.addSvgIcon(
        'leonding-white',
        sanitizer.bypassSecurityTrustResourceUrl('assets/icons/htl/logo-white.svg'));
      // Youtube icon
      iconRegistry.addSvgIcon(
        'youtube',
        sanitizer.bypassSecurityTrustResourceUrl('assets/icons/social/youtube.svg'));
      iconRegistry.addSvgIcon(
        'youtube-white',
        sanitizer.bypassSecurityTrustResourceUrl('assets/icons/social/youtube-white.svg'));
    }
  }
}
