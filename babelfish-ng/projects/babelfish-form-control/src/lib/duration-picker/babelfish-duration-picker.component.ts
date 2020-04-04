import { Component, forwardRef, Input, OnInit } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
    selector: 'babelfish-duration-picker',
    templateUrl: './babelfish-duration-picker.component.html',
    styleUrls: ['./babelfish-duration-picker.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => BabelfishDurationPickerComponent),
            multi: true
        }
    ]
})
export class BabelfishDurationPickerComponent implements OnInit, ControlValueAccessor {

    @Input()
    public inputId: string;

    public value: string;

    propagateChange = (_: any) => {};

    constructor() { }

    ngOnInit() {}

    writeValue(value: any) {
        this.value = value;
    }

    registerOnChange(fn) {
        this.propagateChange = fn;
    }

    registerOnTouched() {}

    onChange() {
        this.propagateChange(this.value);
    }

}
