for (size_t i = 0; i <= randomString.length() - 100; ++i) {
        if (isValid(randomString.substr(i, 100))) {
            cout << "Substring found at position " << i << ": " << randomString.substr(i, 100) << endl;
            return 0;
        }
    }

    cout << "No such substring exists in the generated string." << endl;

    return 0;