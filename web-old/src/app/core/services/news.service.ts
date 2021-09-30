import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { News } from 'src/app/shared/models';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  /**
   *Creates an instance of NewsService.
   * @memberof NewsService
   */
  constructor(private http: HttpClient) { }

  getNewsList(): Observable<Array<News>> {
    return this.http.get<Array<News>>('https://iot-helper-service.herokuapp.com/news').pipe(
      map(json => {
        const newsList = [];
        json.forEach(element => {
          const news = new News();
          news.title = element.title;
          news.body = element.body;
          newsList.push(news);
        });
        return newsList;
      })
    );
  }
}
