import { useMemo, useState } from "react";
import passwordValidator from "password-validator";

type ValidatorOptionType = number | boolean;

export interface IValidatorOption {
    min: number;
    max: number;
    digits: ValidatorOptionType;
    letters: ValidatorOptionType;
    lowercase: ValidatorOptionType;
    uppercase: ValidatorOptionType;
    symbols: ValidatorOptionType;
    spaces: ValidatorOptionType;
};

export interface errorObject {
    validation: string;
    arguments: number;
    message: string;
}

// sets default rules. Picks out min max spaces and letters
const defaultRules: Pick<IValidatorOption, 'min' | 'max' | 'spaces' | 'letters'> = {
    min: 6,
    max: 100,
    spaces: false,
    letters: true,
};


export type IUsePasswordValidator = [ boolean, errorObject[],( password: string ) => void ]

// Partial is a utility type that transforms a type by making all of its properties optional
export function usePasswordValidator( option?: Partial<IValidatorOption> ): IUsePasswordValidator {
    const [ isValid, setIsValid ] = useState<boolean>(false)
    const [ errors, setErrors ] = useState<errorObject[]>([])
    const _option: Partial<IValidatorOption> = useMemo(() => {
        // merges defaultRules and option into one object and returns
        return Object.assign({}, defaultRules, option)
    },[])

    const schema = useMemo( () => {
        function checkUseRule( val: ValidatorOptionType | undefined ): [ boolean | undefined, number | undefined ] {
            if( val === undefined ) return [ undefined, undefined ]
            if( val === false ) return [ false, undefined ]
            if( val === 0 ) return [ false, undefined ]
            if( val === true ) {
                return [ true, undefined ]
            };
            return [ true, val ]
        };
        const _schema = new passwordValidator();
        
        for( const [ key, value ] of Object.entries(_option) ){ 
            if ( key === 'min' ) { 
                _schema.is().min(value as number) 
                continue;
            };
            if ( key === 'max' ) { 
                _schema.is().max(value as number);
                continue;
            };
            const [ use, count ] = checkUseRule(value);
            if( use === undefined ) continue;
            
            switch(key){
                case 'spaces':
                    if ( use ) _schema.has().spaces(count);
                    else _schema.has().not().spaces();
                    break;
                case 'symbols':
                    if ( use ) _schema.has().symbols(count);
                    else _schema.has().not().symbols();
                    break;
                case 'lowercase':
                    if ( use ) _schema.has().lowercase(count);
                    else _schema.has().not().lowercase();
                    break;
                case 'uppercase':
                    if ( use ) _schema.has().uppercase(count);
                    else _schema.has().not().uppercase();
                    break;
                case 'letters':
                    if ( use ) _schema.has().letters(count);
                    else _schema.has().not().letters();
                    break;
                case 'digits':
                    if ( use ) _schema.has().digits(count);
                    else _schema.has().not().digits();
                    break;
                default:
                    break;
            };
        };
        return _schema
    }, [ _option ] )

    return [
        isValid, 
        errors,
        ( password: string ) => {
            const valid = schema.validate(password, { details: true });
            setIsValid(Array.isArray(valid) ? false : true)
            if (Array.isArray(valid)){
                setErrors(valid)
            }
        }
    ]
};