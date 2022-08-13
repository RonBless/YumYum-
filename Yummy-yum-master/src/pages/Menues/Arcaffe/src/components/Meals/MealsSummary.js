import classes from './MealsSummary.module.css';
import React from 'react';


const MealsSummary = () => {
  return (
    <section className={classes.summary}>
      <h2>Arcaffe, A quality coffee </h2>
      <p>
      	Our story begins with an endless love and passion for coffee and a dream to bring a real Italian espresso bar to Israel.
      </p>
      <p>
     	Five generations of the best coffee makers in the world, we have created a unique blend of fine coffee beans that can not be drunk anywhere else.
      </p>
    </section>
  );
};

export default MealsSummary;
