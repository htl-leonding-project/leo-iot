import { Language } from 'src/app/shared/models';
import { Injectable } from '@angular/core';

import { languages } from 'src/app/config/languages';

@Injectable()
export class LanguageService {

  /**
   * Creates an instance of LanguageService.
   * @memberof LanguageService
   */
  constructor() {
    const defaultLanguage = languages.filter(l => l.isDefault)[0];
    if (defaultLanguage) {
      window.localStorage.setItem('language', JSON.stringify(defaultLanguage));
    }
  }

  /**
   * returns the language
   *
   * @returns
   * @memberof LanguageService
   */
  getLanguage(): Language {
    const lang = JSON.parse(window.localStorage.getItem('language'));
    if (lang !== null) {
      return lang;
    } else {
      return { name: 'System', code: 'sys', flag: '/' }; // default language implementation at the server
    }
  }

  /**
   *We remove the system language because this is only for displaying the languages on the settings module
   *
   * @returns {Language[]}
   * @memberof LanguageService
   */
  getAllLanguages(): Language[] {
    return languages.filter(l => l.code !== 'sys');
  }

  /**
   * this method switches the language to english
   *
   * @memberof LanguageService
   */
  changeLanguageEnglish() {
    window.localStorage.setItem('language', 'en');
    console.log(window.localStorage.getItem('language'));
  }

  /**
   * this method switches the language to german
   *
   * @memberof LanguageService
   */
  changeLanguageGerman() {
    window.localStorage.setItem('language', 'de');
    console.log(window.localStorage.getItem('language'));
  }

  /**
   * this method switches the language to a given language
   *
   * @param {Language} language
   * @memberof LanguageService
   */
  changeLanguage(language: Language) {
    if (languages.includes(language)) {
      window.localStorage.setItem('language', JSON.stringify(language));
      console.log(window.localStorage.getItem('language'));
    } else {
      console.warn('Given language not defined in the config file', language);
    }
  }
}
