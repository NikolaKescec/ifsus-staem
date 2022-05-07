import * as React from "react";

import { useSelector } from "react-redux";

import { Center, LoadingOverlay } from "@mantine/core";

import { useAuth0 } from "@auth0/auth0-react";

import * as categoryActions from "../store/shared/category.actions";
import * as developerActions from "../store/shared/developer.actions";
import * as genreActions from "../store/shared/genre.actions";
import * as publisherActions from "../store/shared/publisher.actions";
import * as categorySelectors from "../store/shared/category.selectors";
import * as developerSelectors from "../store/shared/developer.selectors";
import * as genreSelectors from "../store/shared/genre.selectors";
import * as publisherSelectors from "../store/shared/publisher.selectors";
import { useAppDispatch } from "../store/store";

export default function SharedProvider({
  children,
}: React.PropsWithChildren<{}>) {
  const dispatch = useAppDispatch();
  const { isAuthenticated, getAccessTokenSilently } = useAuth0();

  const categoryStatus = useSelector(categorySelectors.status);
  const developerStatus = useSelector(developerSelectors.status);
  const genreStatus = useSelector(genreSelectors.status);
  const publisherStatus = useSelector(publisherSelectors.status);

  React.useEffect(() => {
    dispatch(categoryActions.findAll());
    dispatch(developerActions.findAll());
    dispatch(genreActions.findAll());
    dispatch(publisherActions.findAll());
  }, [dispatch]);

  React.useEffect(() => {
    const getToken = async () => {
      const token = await getAccessTokenSilently();
      localStorage.setItem("access_token", token);
    };
    getToken();
  }, [isAuthenticated, getAccessTokenSilently]);

  if (
    categoryStatus !== "success" ||
    developerStatus !== "success" ||
    genreStatus !== "success" ||
    publisherStatus !== "success"
  ) {
    return (
      <Center>
        <LoadingOverlay visible={true} />
      </Center>
    );
  }

  return <>{children}</>;
}
