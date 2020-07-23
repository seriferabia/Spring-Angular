import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SubredditModel} from './subreddit-response';
import {identity, Observable} from 'rxjs';
import {Encoding} from 'tslint/lib/utils';



@Injectable({
  providedIn: 'root'
})
export class SubredditService {

  constructor(private http: HttpClient) {

  }

  getAllSubreddits(): Observable<Array<SubredditModel>> {
    return this.http.get<Array<SubredditModel>>('http://localhost:8080/api/subreddit');
  }

  createSubreddit(subredditModel: SubredditModel): Observable<SubredditModel> {
    return this.http.post<SubredditModel>('http://localhost:8080/api/subreddit',
      subredditModel);
  }
}
