import { TestBed } from '@angular/core/testing';

import { LanguageService } from './language.service';

describe('LanguageService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [LanguageService]
  }));

  it('should be created', () => {
    const service: LanguageService = TestBed.get(LanguageService);
    expect(service).toBeTruthy();
  });

  // Tests the method changeLanguageEnglish, the method should switch the langauge to en
  it('should switch language to en', () => {
    const service: LanguageService = TestBed.get(LanguageService);
    service.changeLanguageEnglish();
    let expected = false;
    if (window.localStorage.getItem('language') === 'en') {
      expected = true;
    }
    expect(expected).toBeTruthy();
  });

  // Tests the method changeLanguageGerman, the method should switch the langauge to de
  it('should switch language to de', () => {
    const service: LanguageService = TestBed.get(LanguageService);
    service.changeLanguageGerman();
    let expected = false;
    if (window.localStorage.getItem('language') === 'de') {
      expected = true;
    }
    expect(expected).toBeTruthy();
  });

});
