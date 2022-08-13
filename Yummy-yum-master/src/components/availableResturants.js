import React from 'react';
import Card from './cardRest';
import classes from './availableResturants.css';
import { useState, useEffect } from 'react';
import Navbar from './navbar';
import { useNavigate, useParams } from 'react-router-dom';


import { Link, BrowserRouter as Router } from 'react-router-dom';

const AvailableMeals = () => {
	
	const{id} = useParams();

    const navigate = useNavigate();

    const routeChange = () => {
        navigate('/');
    }

    const [resturaunts, setResturaunts] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [httpError, setHttpError] = useState();
	


    useEffect(() => {
        const fetchResturaunts = async() => {
			
            var response = await fetch(
                'http://localhost:8084/iob/instances?userDomain=2022B.Amit.Yehezkel&userEmail=' + id +'&size=500'
            );

            if (!response.ok) {
				response = await fetch(
                'http://localhost:8084/iob/instances?userDomain=2022B.Amit.Yehezkel&userEmail=' + id +'&size=500'
            );
				if(!response.ok){
					throw new Error('Sorry, Please go to the previous page and Enter a valid Email address');
				}

            }

            const responseData = await response.json();

            const allResturaunts = [];

            for (var i = 0; i < responseData.length; i++) {
                if (responseData[i].type === "resturaunt") {
                    allResturaunts.push({
                        id: responseData[i].instanceAttributes.id,
                        name: responseData[i].instanceAttributes.name,
                        description: responseData[i].instanceAttributes.description,
                        label: responseData[i].instanceAttributes.label,
                        phone: responseData[i].instanceAttributes.phone,
                        address: responseData[i].instanceAttributes.address,
                        path: responseData[i].instanceAttributes.path,
                        picture: responseData[i].instanceAttributes.picture
                    });
                }
            }

            setResturaunts(allResturaunts);
            setIsLoading(false);
        };
        			
        	
			fetchResturaunts().catch((error) => {
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


	
	
    return (
        <React.Fragment>
            <Navbar />
            <div className="topWrapper">
                <button type="submit" name="submit" className="logout" onClick={routeChange}>
                    Logout
                </button>
            </div>
            <ul>
                {resturaunts.map(item => (
                    <Card key={item.id}>
                        <div className="wrapper">
                            <div className="left">
                                <div className="name">{item.name}</div>
                                <div className="label">{item.label}</div>
                                <div className="description">{item.description}</div>
                                <Link className="read_more" to={item.path}>Go to menu</Link>
                                <div className="details">
                                    <a href={item.phone}>{item.phone}</a>
                                    <li className="address">{item.address}</li>
                                </div>
                            </div>
                            <div className="right">
                                <img className="picture" src={item.picture} alt="" />
                            </div>
                        </div>
                    </Card>
                ))
                }
            </ul>
        </React.Fragment>
    );
};

export default AvailableMeals;
