import '../../App.css';

function App() {
    const movies = [
        {title: 'Iron man', year: 2001},
        {title: 'Mission Impossible', year: 2002},
        {title: 'Mother', year: 2008},
    ];

    const renderMovies = movies.map(movie => {
        return (
            <div key={movie.title} className={"movie"}>
                <div className={"title"}>{movie.title}</div>
                <div className={"year"}>{movie.year}</div>
            </div>
        );
    });
    return (
    <div className="App">
        <h1>Movie List ! </h1>

        {renderMovies}
        {/*<div className={"movie"}>
            <div className={"title"}>{movies[0].title}</div>
            <div className={"year"}>{movies[0].year}</div>
        </div>*/}
    </div>
  );
}

export default App;
