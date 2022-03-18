import '../../App.css';
import Movie from "../conponents/Movie";

function App() {
    const movies = [
        {title: 'Iron man', year: 2001},
        {title: 'Mission Impossible', year: 2002},
        {title: 'Mother', year: 2008},
    ];

    const renderMovies = movies.map(movie => {
        return (
            <Movie movie={movie} key={movie.title}/>
        );
    });
    return (
    <div className="App">
        <h1>Movie List ! </h1>
        {renderMovies}
    </div>
  );
}

export default App;
