
compile:
	g++ -Wall Profiler.cpp *.h -o profiler
	g++ -Wall Tests.cpp *.h -o tests
	
test:
	@./tests
	
profile:
	@./profiler
	
clean:
	rm tests
	rm profiler
	
a:
	@g++ -Werror -Wall  Profiler.cpp *.h -o profiler
	@./profiler
	