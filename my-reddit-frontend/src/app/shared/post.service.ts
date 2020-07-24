import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PostModel} from './post-model';
import { CreatePostPayload } from '../post/create-post/create-post.payload';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient) { }

  getAllPosts(): Observable<Array<PostModel>>{
    return this.httpClient.get<Array<PostModel>>('http://localhost:8080/api/post/');
  }

  createPost(postPayload: CreatePostPayload): Observable<any> {
    return this.httpClient.post('http://localhost:8080/api/post', postPayload);
  }
}
