/**
 * Babelfish Type Service API
 * This is the Babelfish Type Service API. You can find out more about Babelfish at [https://github.com/bizaio/deepthought](https://github.com/bizaio/babelfish) or on the [DataRight.io Slack, #oss](https://join.slack.com/t/datarightio/shared_invite/enQtNzAyNTI2MjA2MzU1LTU1NGE4MmQ2N2JiZWI2ODA5MTQ2N2Q0NTRmYmM0OWRlM2U5YzA3NzU5NDYyODlhNjRmNzU3ZDZmNTI0MDE3NjY).
 *
 * OpenAPI spec version: 1.2.6-SNAPSHOT
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *//* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';
import { CustomHttpUrlEncodingCodec }                        from '../encoder';

import { Observable }                                        from 'rxjs';

import { ResponseGetTypes } from '../model/responseGetTypes';
import { TypeField } from '../model/typeField';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class TypeService {

    protected basePath = 'http://localhost:8080/dio-au';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (const consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * Get a value/label set for one or more enumeration types
     * Returns a value/label set of FormValueLabel&#x27;s based on the enumerations requested
     * @param labelTypes Set of Value Label Type lists to get
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getEnumerationTypes(labelTypes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<ResponseGetTypes>;
    public getEnumerationTypes(labelTypes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ResponseGetTypes>>;
    public getEnumerationTypes(labelTypes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ResponseGetTypes>>;
    public getEnumerationTypes(labelTypes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (labelTypes === null || labelTypes === undefined) {
            throw new Error('Required parameter labelTypes was null or undefined when calling getEnumerationTypes.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (labelTypes) {
            labelTypes.forEach((element) => {
                queryParameters = queryParameters.append('labelTypes', <any>element);
            })
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.request<ResponseGetTypes>('get',`${this.basePath}/v1/type/enumerations`,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Get a List of Available Types
     * Returns a list of available types and the type of type they are
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public listTypes(observe?: 'body', reportProgress?: boolean): Observable<Array<TypeField>>;
    public listTypes(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<TypeField>>>;
    public listTypes(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<TypeField>>>;
    public listTypes(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.request<Array<TypeField>>('get',`${this.basePath}/v1/type`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
