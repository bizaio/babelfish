import {BabelfishFormControlTypes} from '../types/babelfish-control-types';
import {BabelfishFormControl} from './babelfish-form-control';

export class BabelfishFormInput extends BabelfishFormControl {

    subType: string;

    constructor(defaultValue, label: string, validators = [], subType = 'text', asyncValidators = []) {
        super(BabelfishFormControlTypes.INPUT, defaultValue, label, validators, asyncValidators);

        this.subType = subType;
    }
}
