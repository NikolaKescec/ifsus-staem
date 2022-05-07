import * as React from "react";

import { useSelector } from "react-redux";

import { Center, LoadingOverlay } from "@mantine/core";

import { useAuth0 } from "@auth0/auth0-react";

import * as registryActions from "../store/shared/registry.actions";
import * as registrySelectors from "../store/shared/registry.selectors";
import { useAppDispatch } from "../store/store";

export default function SharedProvider({
  children,
}: React.PropsWithChildren<{}>) {
  const dispatch = useAppDispatch();
  const { isAuthenticated, getAccessTokenSilently } = useAuth0();

  const categoryStatus = useSelector(registrySelectors.categoryStatus);
  const developerStatus = useSelector(registrySelectors.developerStatus);
  const genreStatus = useSelector(registrySelectors.genreStatus);
  const publisherStatus = useSelector(registrySelectors.publisherStatus);

  React.useEffect(() => {
    dispatch(registryActions.getCategories());
    dispatch(registryActions.getDevelopers());
    dispatch(registryActions.getGenres());
    dispatch(registryActions.getPublishers());
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
