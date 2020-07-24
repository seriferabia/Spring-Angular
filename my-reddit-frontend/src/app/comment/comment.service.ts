import { Injectable } from '@angular/core';
import {CommentPayload} from './comment.payload';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient: HttpClient) { }

  postComment(commentPayload: CommentPayload): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/api/comments', commentPayload);
  }

  getAllCommentsForPost(postId: number): Observable<CommentPayload[]> {
    return this.httpClient.get<CommentPayload[]>('http://localhost:8080/api/comments/by-postId/' + postId);
  }
}
