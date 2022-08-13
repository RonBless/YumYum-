import { useRef, useState } from 'react';
import  classes from './Checkout.css';
import React from 'react';

const isEmpty = value => value.trim() === '';
//const isNotFiveChars = value => value.trim.length !== 5; --> just as an example for a char limit in input 

const Checkout = (props) => {
    const [formInputsValidity, setFormInputsVadility] = useState({
        name: true,
        phone: true, 
        street: true, 
        postalCode: true, 
        city: true
    });

    const nameInputRef = useRef(); //the refs will help to pass the data to the dtb
    const phoneInputRef = useRef();
    const streetInputRef = useRef();
    const postalInputRef = useRef();
    const cityInputRef = useRef();

    const confirmHandler = (event) => {
        event.preventDefault(); //to ensure that the browser default to send http requests is prevented

        const enteredName = nameInputRef.current.value;
        const enteredPhone = phoneInputRef.current.value;
        const enteredStreet = streetInputRef.current.value;
        const enteredPostal = postalInputRef.current.value;
        const enteredCity = cityInputRef.current.value;

        const enteredNameIsValid = !isEmpty(enteredName);
        const enteredPhoneIsValid = !isEmpty(enteredPhone);
        const enteredStreetIsValid = !isEmpty(enteredStreet);
        const enteredPostalIsValid = !isEmpty(enteredPostal);
        const enteredCityIsValid = !isEmpty(enteredCity);

        setFormInputsVadility({
            name: enteredNameIsValid, 
            phone: enteredPhoneIsValid,
            street: enteredStreetIsValid,
            postalCode: enteredPostalIsValid,
            city: enteredCityIsValid
        });
        
        const formIsValid =
            enteredNameIsValid &&
            enteredPhoneIsValid &&
            enteredStreetIsValid &&
            enteredPostalIsValid &&
            enteredCityIsValid;


        if (!formIsValid) {
            return;
        }

        props.onConfirm({
            name: enteredName, 
            phone: enteredPhone, 
            street: enteredStreet, 
            postal: enteredPostal, 
            city: enteredCity
        });
    };

    return (
        <form className={classes.form} onSubmit={confirmHandler}>
            <div class="control">
                <label htmlFor='name'>Your Name</label>
                <input type='text' id='name' ref={nameInputRef} />
                {!formInputsValidity.name && <p class="errorMsg">Please enter a name</p>}
            </div>
            <div class="control">
                <label htmlFor='phone'>Phone Number</label>
                <input type='number' id='phone' ref={phoneInputRef} />
                {!formInputsValidity.phone && <p className={classes.errorMsg}>Please enter a phone number</p>}
            </div>
            <div class="control">
                <label htmlFor='street'>Street</label>
                <input type='text' id='street' ref={streetInputRef} />
                {!formInputsValidity.street && <p className={classes.errorMsg}>Please enter a street address</p>}
            </div>
            <div class="control">
                <label htmlFor='postal'>Postal Code</label>
                <input type='text' id='postal' ref={postalInputRef} />
                {!formInputsValidity.postalCode && <p className={classes.errorMsg}>Please enter a postal code</p>}
            </div>
            <div class="control">
                <label htmlFor='city'>City</label>
                <input type='text' id='city' ref={cityInputRef} />
                {!formInputsValidity.city && <p className={classes.errorMsg}>Please enter a city</p>}
            </div>
            <div class="actions">
                <button type='button' onClick={props.onCancel}>
                    Cancel
                </button>
                <button class="submit">Confirm</button>
            </div>
        </form>
    );
};

export default Checkout;