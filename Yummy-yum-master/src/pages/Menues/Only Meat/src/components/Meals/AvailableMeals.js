import { useEffect, useState } from 'react';
import React from 'react';


import Card from '../UI/Card';
import MealItem from './MealItem/MealItem';
import classes from './AvailableMeals.module.css';
import { useParams } from 'react-router-dom';


const AvailableMeals = () => {

  const{id} = useParams();
  const [resturaunts, setResturaunts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState();

  useEffect(() => {
    const fetchMeals = async () => {
      const response = await fetch(
          'http://localhost:8084/iob/instances?userDomain=2022B.Amit.Yehezkel&userEmail=' + id +'&size=500'
      );

      if (!response.ok) {
        throw new Error('Something went wrong!');
      }

      const responseData = await response.json();

      const loadedMeals = [];

      for (var i = 0; i < responseData.length; i++) {
                if (responseData[i].type === "dish" && responseData[i].name === "onlymeat") {
                    loadedMeals.push({
                        id: responseData[i].instanceAttributes.id,
                        name: responseData[i].instanceAttributes.name,
                        description: responseData[i].instanceAttributes.description,
                        price: responseData[i].instanceAttributes.price
                    });
                }
            }

      setResturaunts(loadedMeals);
      setIsLoading(false);
    };

    fetchMeals().catch((error) => {
      setIsLoading(false);
      setHttpError(error.message);
    });
  }, []);

  if (isLoading) {
    return (
      <section className={classes.MealsLoading}>
        <p>Loading...</p>
      </section>
    );
  }

  if (httpError) {
    return (
      <section className={classes.MealsError}>
        <p>{httpError}</p>
      </section>
    );
  }

  const mealsList = resturaunts.map((meal) => (
    <MealItem
      key={meal.id}
      id={meal.id}
      name={meal.name}
      description={meal.description}
      price={meal.price}
    />
  ));

  return (
    <section className={classes.meals}>
      <Card>
        <ul>{mealsList}</ul>
      </Card>
    </section>
  );
};

export default AvailableMeals;