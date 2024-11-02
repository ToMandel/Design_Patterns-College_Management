package Model;

interface Observer<T> {
	String handle(PropertyChangedEventArgs<T> args);
}
