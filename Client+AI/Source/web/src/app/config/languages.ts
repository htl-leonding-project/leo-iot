import { Language } from '../shared/models';

/**
 * contains the standard settings for the language and all language options
 */
export const languages: Language[] = [
    // System language is the default at the api. ONLY FOR TESTING!
    {
        name: 'System',
        code: 'sys',
        flag: '/',
        isDefault: true
    },
    {
        name: 'English',
        code: 'en',
        flag: '/assets/country-flags/us.svg',
        isDefault: true
    },
    {
        name: 'Deutsch',
        code: 'de',
        flag: '/assets/country-flags/de.svg',
    },
];
