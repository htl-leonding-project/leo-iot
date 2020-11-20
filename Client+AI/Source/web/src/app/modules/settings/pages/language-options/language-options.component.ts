import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Language } from 'src/app/shared/models';
import { startWith, map } from 'rxjs/operators';
import { FormControl } from '@angular/forms';

import { LanguageService } from 'src/app/core/services/language.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-language-options',
  templateUrl: './language-options.component.html',
  styleUrls: ['./language-options.component.scss']
})
export class LanguageOptionsComponent implements OnInit {

  /**
   *Array containing all languages read from the config
   *
   * @type {Language[]}
   * @memberof OptionsComponent
   */
  languages: Language[];

  /**
   *Array containing the filtered languages by the users input
   *
   * @type {Observable<Language[]>}
   * @memberof OptionsComponent
   */
  filteredLanguages: Observable<Language[]>;

  /**
   *Language FormControll for data binding
   *
   * @memberof OptionsComponent
   */
  langControl = new FormControl();

  /**
   *Creates an instance of LanguageOptionsComponent.
   * @param {LanguageService} languageService
   * @memberof LanguageOptionsComponent
   */
  constructor(private languageService: LanguageService, private router: Router) { }

  /**
   *Getting option values
   *
   * @memberof OptionsComponent
   */
  ngOnInit() {
    this.getLanguages();
  }

  /**
   *Reading languages from config file
   *
   * @memberof OptionsComponent
   */
  getLanguages() {
    this.languages = this.languageService.getAllLanguages();
    this.langControl.setValue(this.languages.filter(l => l.isDefault)[0].name);
    this.filteredLanguages = this.langControl.valueChanges
      .pipe(
        startWith(''),
        map(lang => lang ? this._filterLanguages(lang) : this.languages.slice())
      );
  }

  /**
   *Filters the array by name with the users input
   *
   * @private
   * @param {string} input
   * @returns {Language[]}
   * @memberof OptionsComponent
   */
  private _filterLanguages(input: string): Language[] {
    const filterValue = input.toLowerCase();
    return this.languages.filter(lang => lang.name.toLowerCase().indexOf(filterValue) === 0);
  }

  /**
   *Handling a language selection
   *
   * @param {Language} language
   * @memberof LanguageOptionsComponent
   */
  selectLanguage(language: Language) {
    this.languageService.changeLanguage(language);
    location.href = `/${language.code}/settings`;
  }
}
