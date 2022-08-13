import { Fragment } from 'react';
import React from 'react';


import AvailableMeals from './availableResturants';


let count=0;
(function inidiate() {
    if(count===0) {
        let count=1;
        console.log(count);
    }
    console.log(count);
})
const Resturants = () => {
  return (
    <Fragment>
      <AvailableMeals />
    </Fragment>
  );
};

export default Resturants;
