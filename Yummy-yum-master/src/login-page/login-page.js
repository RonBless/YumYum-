import firstImage from './images//undraw_special_event_-4-aj8.svg';
import secondImage from './images/undraw_breakfast_psiw.png';
import React, {useEffect, useState} from 'react';
import { useNavigate } from "react-router-dom";
import './login-page.css';
import 'https://kit.fontawesome.com/a81368914c.js';
import wave from './images/wave.png';



const Login = () => {

    let navigate = useNavigate();
    const routeChange = () => {
        let path = `resturaunts/` + input;
        navigate(path);
    }
    
    const [input, setInput] = useState(''); // '' is the initial state value

        function fetchResturaunts (){
            const response = fetch(
                'http://localhost:8084/iob/users', {
			method: 'POST',
			headers: {
    			'Accept': 'application/json',
    			'Content-Type': 'application/json',
  			},
  			body: JSON.stringify({
    		email: input,
    		role: 'PLAYER',
    		username: input,
    		avatar: 'avatar'
			
  	})
		}
            );
            
            if(!response.ok){
				let  domainAndInput = "2022B.Amit.Yehezkel".concat(input);
				let url = 'http://localhost:8084/iob/users/'.concat(domainAndInput);
	           fetch(
                url, {
					method: 'GET',
				}
            );
			}
			
            if (!response.ok) {
			try{
				                 console.log(response.text().then(text => { throw new Error(text) }));

			}catch(err){
				       console.log('caught it!',err);

			}
                 
            }
            
            if (response.ok) {
                throw new Error(' lo');
            }
   
	
        };
        
    const onClickFunction = () => {
		routeChange();
				fetchResturaunts();

	}       
        


    return (
        <React.Fragment>
             <img class="wave" src={wave}></img>
            <div className="container">
                <div className="img" id="left">
                    <img src={firstImage} />
                </div>
                <div className="login-content">
                    <form>
                        <img src={secondImage} />
                        <h2 class="title">Welcome</h2>
                <div class="input-div one">
                    <div class="i">
                        <i class="fab fa-envelope"></i>
                    </div>
                    <div class="div">
                        
                        <input placeholder="Email Address" type="email" className="inputM" value={input} onInput={e => setInput(e.target.value)}/>
                    </div>
                </div>
                        <button type="submit" name="login" class="btn" onClick={onClickFunction} >
                            Login
                        </button>
                    </form>
                </div>
            </div>
        </React.Fragment>
        
    )
};

export default Login; 