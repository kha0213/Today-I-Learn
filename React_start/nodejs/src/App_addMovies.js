import './App.css';
import Movie from "./conponents/Movie";
import {useState} from "react";

function App() {
    const [movieTitle, setMovieTitle] = useState('');
    const [movieYear, setMovieYear] = useState('');
    const [movies, setMovies] = useState([
        {title: 'Iron man', year: 2001},
        {title: 'Mission Impossible', year: 2002},
        {title: 'Mother', year: 2008},
        {title: 'Spider man', year: 2020}
        ]
    );

    const renderMovies = movies.map(movie => {
        return (
            <Movie movie={movie} key={movie.title}/>
        );
    });

    const addMovie = (event) => {
        event.preventDefault();
        if(!movieTitle || !movieYear)
            return;
        setMovies([...movies, {
            title: movieTitle,
            year: movieYear,
        }])
        setMovieTitle('');
        setMovieYear('');
    }

    return (
    <div className="App">
        <h1>Movie List ! </h1>
        {renderMovies}
        <form onSubmit={addMovie}>
            <input type="text" placeholder={"영화제목"}
                   value={movieTitle}
                onChange={e => setMovieTitle(e.target.value)}/>
            <input type="text" placeholder={"영화개봉년도"}
                   value={movieYear}
                onChange={e => setMovieYear(e.target.value)}/>
            <button type={"submit"}>영화추가</button>
        </form>
    </div>
  );
}

export default App;
